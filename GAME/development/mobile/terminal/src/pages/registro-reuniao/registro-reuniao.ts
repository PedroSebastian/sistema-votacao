import { Component } from '@angular/core';
import { AlertController, IonicPage, LoadingController, NavController, NavParams, ViewController } from 'ionic-angular';
import { ItemDePautaPage } from "../item-de-pauta/item-de-pauta";
import { MembroProvider } from "../../providers/membro/membro";

@IonicPage()
@Component({
    selector: 'page-registro-reuniao',
    templateUrl: 'registro-reuniao.html',
})
export class RegistroReuniaoPage {

    reuniaoDados: any;
    tokenMembro: string;
    membro: any;
    carregado: boolean = false;

    loading: any;

    constructor(public navCtrl: NavController, public navParams: NavParams, public viewCtrl: ViewController,
                private membroProvider: MembroProvider, public loadingController: LoadingController,
                private alertController: AlertController) {
    }

    ionViewDidLoad() {
        this.pegaDadosDaReuniao();
    }

    pegaDadosDaReuniao() {
        this.reuniaoDados = this.navParams.data;
        console.log(this.reuniaoDados);
        this.carregado = true;
    }

    closeModal() {
        this.viewCtrl.dismiss();
    }

    entrarNaReniao() {
        this.mostraLoading();

        this.membroProvider.pegaMembroPelo(this.tokenMembro, this.reuniaoDados.reuniao.id)
            .toPromise().then(resultado => {
            this.escondeLoading();
            console.log(resultado);

            this.navCtrl.setRoot(ItemDePautaPage, {
                membro: resultado,
                reuniaoDados: this.reuniaoDados
            }, {animate: true, animation: 'wp-transition', direction: 'forward'});
        }).catch(erro => {
            console.log(erro);
            this.escondeLoading();
            this.exibeAlert();
        });
    }

    mostraLoading() {
        this.loading = this.loadingController.create({
            spinner: 'dots',
            content: 'Carregando dados...'
        });

        this.loading.present();
    }

    escondeLoading() {
        this.loading.dismiss();
    }

    exibeAlert() {
        let alert = this.alertController.create({
            title: 'Oops',
            subTitle: 'Seu token de acesso não está disponível para essa reunião.',
            buttons: ['Ok']
        });
        alert.present();
    }

}
