import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProviderIm } from 'app/shared/model/provider-im.model';

@Component({
    selector: 'jhi-provider-im-detail',
    templateUrl: './provider-im-detail.component.html'
})
export class ProviderImDetailComponent implements OnInit {
    provider: IProviderIm;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ provider }) => {
            this.provider = provider;
        });
    }

    previousState() {
        window.history.back();
    }
}
