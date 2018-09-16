import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IProductByBooking } from 'app/shared/model/product-by-booking.model';
import { Principal } from 'app/core';
import { ProductByBookingService } from './product-by-booking.service';

@Component({
    selector: 'jhi-product-by-booking',
    templateUrl: './product-by-booking.component.html'
})
export class ProductByBookingComponent implements OnInit, OnDestroy {
    productByBookings: IProductByBooking[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private productByBookingService: ProductByBookingService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.productByBookingService.query().subscribe(
            (res: HttpResponse<IProductByBooking[]>) => {
                this.productByBookings = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInProductByBookings();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IProductByBooking) {
        return item.id;
    }

    registerChangeInProductByBookings() {
        this.eventSubscriber = this.eventManager.subscribe('productByBookingListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
