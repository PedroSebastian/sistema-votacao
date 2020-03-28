import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from "rxjs/Observable";
import { Platform } from "ionic-angular";

@Injectable()
export class ReunioesProvider {

  constructor(public http: HttpClient, private platform: Platform) {

  }

  reunioesDisponiveis(): Observable<any> {
    let url;

    if (this.platform.is('android')) {
      url = 'http://10.0.2.2:8181/reunioes';
    } else {
      url = 'http://localhost:8181/reunioes';
    }

    return this.http.get(url);
  }

}
