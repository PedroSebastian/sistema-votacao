import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { ItemDePautaPage } from './item-de-pauta';

@NgModule({
  declarations: [
    ItemDePautaPage,
  ],
  imports: [
    IonicPageModule.forChild(ItemDePautaPage),
  ],
})
export class ItemDePautaPageModule {}
