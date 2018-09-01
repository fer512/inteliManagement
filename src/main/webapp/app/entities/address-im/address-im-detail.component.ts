import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAddressIm } from 'app/shared/model/address-im.model';

@Component({
    selector: 'jhi-address-im-detail',
    templateUrl: './address-im-detail.component.html'
})
export class AddressImDetailComponent implements OnInit {
    address: IAddressIm;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ address }) => {
            this.address = address;
        });
    }

    previousState() {
        window.history.back();
    }
}
