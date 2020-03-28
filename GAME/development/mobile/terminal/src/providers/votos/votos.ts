import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Platform } from "ionic-angular";

@Injectable()
export class VotosProvider {

  constructor(public http: HttpClient, private platform: Platform) {

  }

  public enviarVoto(voto: any) {
      let baseUrl;

      if (this.platform.is('android')) {
          baseUrl = 'http://10.0.2.2:8181/votos';
      } else {
          baseUrl = 'http://localhost:8181/votos';
      }

      let headers = new HttpHeaders({
          "Content-Type": "application/json",
          "Accept": "application/json"
      });

      return this.http.post(baseUrl, voto, {
          headers: headers
      });
  }

}
