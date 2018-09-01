import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBookingIm } from 'app/shared/model/booking-im.model';

@Component({
    selector: 'jhi-booking-im-detail',
    templateUrl: './booking-im-detail.component.html'
})
export class BookingImDetailComponent implements OnInit {
    booking: IBookingIm;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ booking }) => {
            this.booking = booking;
        });
    }

    previousState() {
        window.history.back();
    }
}
