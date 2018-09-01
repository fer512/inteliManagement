import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProvinceIm } from 'app/shared/model/province-im.model';

@Component({
    selector: 'jhi-province-im-detail',
    templateUrl: './province-im-detail.component.html'
})
export class ProvinceImDetailComponent implements OnInit {
    province: IProvinceIm;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ province }) => {
            this.province = province;
        });
    }

    previousState() {
        window.history.back();
    }
}
