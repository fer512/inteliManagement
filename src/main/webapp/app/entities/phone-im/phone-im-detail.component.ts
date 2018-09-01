import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPhoneIm } from 'app/shared/model/phone-im.model';

@Component({
    selector: 'jhi-phone-im-detail',
    templateUrl: './phone-im-detail.component.html'
})
export class PhoneImDetailComponent implements OnInit {
    phone: IPhoneIm;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ phone }) => {
            this.phone = phone;
        });
    }

    previousState() {
        window.history.back();
    }
}
