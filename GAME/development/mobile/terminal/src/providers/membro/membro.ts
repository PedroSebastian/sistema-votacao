import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from "rxjs/Observable";
import { Platform } from "ionic-angular";

@Injectable()
export class MembroProvider {

  constructor(public http: HttpClient, private platform: Platform) {

  }

    pegaMembroPelo(token: string, reuniaoId: number): Observable<any> {
        let url;

        if (this.platform.is('android')) {
            url = 'http://10.0.2.2:8181/membros/';
        } else {
            url = 'http://localhost:8181/membros/';
        }

        return this.http.get(url + token + '/reunioes/' + reuniaoId);
    }

}
