import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IProductByBooking } from 'app/shared/model/product-by-booking.model';
import { ProductByBookingService } from './product-by-booking.service';
import { IProductIm } from 'app/shared/model/product-im.model';
import { ProductImService } from 'app/entities/product-im';
import { IBookingIm } from 'app/shared/model/booking-im.model';
import { BookingImService } from 'app/entities/booking-im';

@Component({
    selector: 'jhi-product-by-booking-update',
    templateUrl: './product-by-booking-update.component.html'
})
export class ProductByBookingUpdateComponent implements OnInit {
    private _productByBooking: IProductByBooking;
    isSaving: boolean;

    products: IProductIm[];

    bookings: IBookingIm[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private productByBookingService: ProductByBookingService,
        private productService: ProductImService,
        private bookingService: BookingImService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ productByBooking }) => {
            this.productByBooking = productByBooking;
        });
        this.productService.query().subscribe(
            (res: HttpResponse<IProductIm[]>) => {
                this.products = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.bookingService.query().subscribe(
            (res: HttpResponse<IBookingIm[]>) => {
                this.bookings = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.productByBooking.id !== undefined) {
            this.subscribeToSaveResponse(this.productByBookingService.update(this.productByBooking));
        } else {
            this.subscribeToSaveResponse(this.productByBookingService.create(this.productByBooking));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IProductByBooking>>) {
        result.subscribe((res: HttpResponse<IProductByBooking>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackProductById(index: number, item: IProductIm) {
        return item.id;
    }

    trackBookingById(index: number, item: IBookingIm) {
        return item.id;
    }
    get productByBooking() {
        return this._productByBooking;
    }

    set productByBooking(productByBooking: IProductByBooking) {
        this._productByBooking = productByBooking;
    }
}
