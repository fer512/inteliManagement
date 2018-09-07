import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVariation } from 'app/shared/model/variation.model';

@Component({
    selector: 'jhi-variation-detail',
    templateUrl: './variation-detail.component.html'
})
export class VariationDetailComponent implements OnInit {
    variation: IVariation;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ variation }) => {
            this.variation = variation;
        });
    }

    previousState() {
        window.history.back();
    }
}
