import { Injectable } from '@angular/core';
import { Observable } from "rxjs/Observable";
import { BehaviorSubject } from "rxjs/BehaviorSubject";

@Injectable()
export class SSEProvider {

  constructor() {

  }

  initStream(url: string): Observable<any> {
    let sse = new window['EventSource'](url);

    return new BehaviorSubject<any>(sse);
  }

}
