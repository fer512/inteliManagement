import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICountryIm } from 'app/shared/model/country-im.model';

@Component({
    selector: 'jhi-country-im-detail',
    templateUrl: './country-im-detail.component.html'
})
export class CountryImDetailComponent implements OnInit {
    country: ICountryIm;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ country }) => {
            this.country = country;
        });
    }

    previousState() {
        window.history.back();
    }
}
