import { Component } from '@angular/core';
import { IonicPage, ModalController, NavController, NavParams } from 'ionic-angular';
import { ReunioesProvider } from "../../providers/reunioes/reunioes";
import { RegistroReuniaoPage } from "../registro-reuniao/registro-reuniao";

/**
 * Generated class for the ReunioesPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */
@IonicPage()
@Component({
  selector: 'page-reunioes',
  templateUrl: 'reunioes.html',
})
export class ReunioesPage {

  reunioes: any[];
  carregado: boolean = false;

  constructor(public navCtrl: NavController, public navParams: NavParams, private reunioesProvider: ReunioesProvider,
              public modalCtrl: ModalController) {

  }

  ionViewDidLoad() {
    this.carregaReunioes();
  }

  carregaReunioes(): void {
    this.reunioesProvider.reunioesDisponiveis().toPromise().then(resultado => {
      this.reunioes = resultado;
      console.log(resultado);

      this.carregado = true;
    }).catch(erro => {
      console.log(erro);
    });
  }

  registraNaReuniao(reuniao): void {
    let modal = this.modalCtrl.create(RegistroReuniaoPage, {
      reuniao: reuniao
    });
    modal.present();
  }

}
