import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProductByBooking } from 'app/shared/model/product-by-booking.model';

@Component({
    selector: 'jhi-product-by-booking-detail',
    templateUrl: './product-by-booking-detail.component.html'
})
export class ProductByBookingDetailComponent implements OnInit {
    productByBooking: IProductByBooking;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ productByBooking }) => {
            this.productByBooking = productByBooking;
        });
    }

    previousState() {
        window.history.back();
    }
}
