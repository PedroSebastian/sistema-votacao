import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { ReunioesPage } from "../reunioes/reunioes";

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  constructor(public navCtrl: NavController) { }

  reunioesDisponiveis(): void {
    this.navCtrl.push(ReunioesPage);
  }

}
