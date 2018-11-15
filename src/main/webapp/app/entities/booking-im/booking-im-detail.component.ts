import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBookingIm } from 'app/shared/model/booking-im.model';

export interface ListVariations {
    id: string;
    code: string;
    extra_charge: number;
    new_charge: number;
    new_cost: number;
    new_benefit: number;
    new_external_locator_id: number;
    comments: string;
    creation_date: string;
    creation_user: string;
    approval_user: string;
    approval_date: string;
    provider: string;
    product: string;
    campaing: string;
    reason: string;
    recoverable: number;
    refund_in_points: number;
    refund_in_cash: number;
}

@Component({
    selector: 'jhi-booking-im-detail',
    templateUrl: './booking-im-detail.component.html'
})
export class BookingImDetailComponent implements OnInit {
    booking: IBookingIm;
    variations: ListVariations[] = [];
    selectedIndex: number = 0;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ booking }) => {
            console.log(booking);
            this.booking = booking;
            /** find index to object load by route param jid (JuniperId) and set active tab */
            let refJidActiveTab = null;
            this.activatedRoute.params.subscribe(params => {
                refJidActiveTab = params['jid'];
            });
            if (refJidActiveTab) {
                const idx = this.booking.products.findIndex(x => x.idReserveLocatorJuniper == refJidActiveTab);
                this.selectedIndex = idx;
            }
        });
    }

    previousState() {
        window.history.back();
    }
}
