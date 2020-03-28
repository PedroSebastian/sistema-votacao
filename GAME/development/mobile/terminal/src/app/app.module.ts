import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';

import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { ItemDePautaPage } from "../pages/item-de-pauta/item-de-pauta";
import { SSEProvider } from '../providers/sse/sse';
import { ReunioesPage } from "../pages/reunioes/reunioes";
import { ReunioesProvider } from '../providers/reunioes/reunioes';
import { HttpClientModule } from "@angular/common/http";
import { RegistroReuniaoPage } from "../pages/registro-reuniao/registro-reuniao";
import { LocalNotifications } from '@ionic-native/local-notifications';
import { VotosProvider } from '../providers/votos/votos';
import { MembroProvider } from '../providers/membro/membro';

@NgModule({
  declarations: [
    MyApp,
    HomePage,
    ItemDePautaPage,
    ReunioesPage,
    RegistroReuniaoPage
  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp),
    HttpClientModule,
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    ItemDePautaPage,
    ReunioesPage,
    RegistroReuniaoPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    SSEProvider,
    ReunioesProvider,
    LocalNotifications,
    VotosProvider,
    MembroProvider
  ]
})
export class AppModule {}
