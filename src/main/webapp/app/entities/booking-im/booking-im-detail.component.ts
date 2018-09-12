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
    variations: ListVariations[] = [
        {
            id: 'aaaa-bbbbbb-cccc-dddddd-eeee',
            code: 'EC-02012',
            extra_charge: 0,
            new_charge: 900,
            new_cost: 800,
            new_benefit: 100,
            new_external_locator_id: 9021125,
            comments: 'Se crea perdida por error de cobro, la tarifa fue mal igresada por el agente.',
            creation_date: '2018-09-11 18:22:26',
            creation_user: 'stamburro',
            approval_user: 'antonio.lopez',
            approval_date: '2018-09-11 18:35:02',
            provider: 'Expidia',
            product: 'Hotel',
            campaing: 'Customer Service',
            reason: 'Error de Cobro',
            recoverable: 1,
            refund_in_points: 100,
            refund_in_cash: 500
        },
        {
            id: '1111-222222-3333-444444-5555',
            code: 'EE-0002',
            extra_charge: 0,
            new_charge: 100,
            new_cost: 50,
            new_benefit: 0,
            new_external_locator_id: 9234325,
            comments: 'Error de fechas al emitir el voucher, se cobra penalidad. se hace la devolución al pasajero.',
            creation_date: '2018-09-12 09:11:46',
            creation_user: 'roberto.carlos',
            approval_user: 'antonio.lopez',
            approval_date: '2018-09-12 12:51:34',
            provider: 'Expidia',
            product: 'Hotel',
            campaing: 'Customer Service',
            reason: 'Error de Emisión',
            recoverable: 1,
            refund_in_points: 0,
            refund_in_cash: 0
        }
    ];

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
