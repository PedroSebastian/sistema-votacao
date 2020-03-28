import { Component, NgZone } from '@angular/core';
import { AlertController, IonicPage, NavController, NavParams, Platform } from 'ionic-angular';
import { SSEProvider } from "../../providers/sse/sse";
import { FormControl, FormGroup } from "@angular/forms";
import { HomePage } from "../home/home";
import { LocalNotifications } from '@ionic-native/local-notifications';
import { VotosProvider } from "../../providers/votos/votos";

@IonicPage()
@Component({
    selector: 'page-item-de-pauta',
    templateUrl: 'item-de-pauta.html',
})
export class ItemDePautaPage {

    reuniao: any;

    itemDePauta: any = null;
    encaminhamentoSelecionado: any = null;
    opcoesForm;

    voto: any = {};

    private isAndroid: boolean;

    confirma: boolean = false;

    submeterVotoBtnTxt: string = "Votar";

    sse: any;

    constructor(public navCtrl: NavController, public navParams: NavParams, private platform: Platform, private zone: NgZone,
                private sseProvider: SSEProvider, public alertCtrl: AlertController,
                private localNotifications: LocalNotifications, private votosProvider: VotosProvider) {
    }

    ionViewDidEnter() {
        this.opcoesForm = new FormGroup({
            "opcaoDeVoto": new FormControl({value: 'rust', disabled: false})
        });

        this.pegaDadosReuniao();
        this.conectaAoServidor();
    }

    ionViewDidLeave() {
        this.sse.close();
    }

    pegaDadosReuniao() {
        this.reuniao = this.navParams.data;
        console.log(this.reuniao);
    }

    conectaAoServidor() {
        let url;

        if (this.platform.is('android')) {
            this.isAndroid = true;
            url = 'http://10.0.2.2:8181/reunioes/' + this.reuniao.reuniaoDados.reuniao.id
                + '/membros/' + this.reuniao.membro.token;
        } else {
            url = 'http://localhost:8181/reunioes/' + this.reuniao.reuniaoDados.reuniao.id
                + '/membros/' + this.reuniao.membro.token;
        }

        this.sseProvider.initStream(url).subscribe(resultado => {
            this.sse = resultado;

            resultado.onmessage = evento => {
                const msg = JSON.parse(evento.data);

                this.zone.run(() => {
                    this.encaminhamentoSelecionado = null;
                    this.itemDePauta = msg;

                    this.notificar("Votação iniciada para o item: " + this.itemDePauta.descricao);
                    console.log(this.itemDePauta);
                });
            };
        });
    }

    seleciona(encaminhamento) {
        this.confirma = false;
        this.submeterVotoBtnTxt = "Votar";
        console.log(encaminhamento);
        this.encaminhamentoSelecionado = encaminhamento;
    }

    submeterVoto(voto) {
        if (this.confirma) {
            let membro = this.reuniao.membro;

            this.voto.escolha = this.encaminhamentoSelecionado;
            this.voto.votante = membro;

            console.log(this.voto);

            this.votosProvider.enviarVoto(this.voto).subscribe(resultado => {
                this.voto = {};
                this.itemDePauta = null;

                console.log(resultado);

                this.submeterVotoBtnTxt = "Votar";

                this.confirma = false;

                let alert = this.alertCtrl.create({
                    title: 'Confirmação',
                    subTitle: 'Voto submetido com sucesso',
                    buttons: [
                        {
                            text: 'Okay',
                            handler: data => {
                                console.log('Cancel clicked');
                            }
                        }
                    ]
                });
                alert.present();
            }, erro => {
                console.log(erro);
            });
        } else {
            this.submeterVotoBtnTxt = "Confirmar Voto"
            this.confirma = true;
        }
    }

    showAlert() {
        let alert = this.alertCtrl.create({
            title: 'Sair da Reunião',
            subTitle: 'Deseja realmente sair e desconectar-se da reunião?',
            buttons: [
                {
                    text: 'Cancelar',
                    handler: data => {
                        console.log('Cancel clicked');
                    }
                },

                {
                    text: 'Sair',
                    handler: data => {
                        this.navCtrl.setRoot(HomePage, {},
                            {animate: true, animation: 'wp-transition', direction: 'forward'});
                    }
                }
            ]
        });
        alert.present();
    }

    notificar(dados) {
        this.localNotifications.schedule({
            id: 1,
            text: dados,
            sound: 'file://assets/sounds/sound.mp3',
            data: {secret: dados}
        });
    }

}
