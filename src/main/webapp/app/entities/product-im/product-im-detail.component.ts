import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProductIm } from 'app/shared/model/product-im.model';

@Component({
    selector: 'jhi-product-im-detail',
    templateUrl: './product-im-detail.component.html'
})
export class ProductImDetailComponent implements OnInit {
    product: IProductIm;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ product }) => {
            this.product = product;
        });
    }

    previousState() {
        window.history.back();
    }
}
