import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IBookingIm } from 'app/shared/model/booking-im.model';
import { BookingImService } from './booking-im.service';
import { ICompanyIm } from 'app/shared/model/company-im.model';
import { CompanyImService } from 'app/entities/company-im';

export interface ListOpt {
    value: string;
    description: string;
}

@Component({
    selector: 'jhi-booking-im-update',
    templateUrl: './booking-im-update.component.html'
})
export class BookingImUpdateComponent implements OnInit {
    private _booking: IBookingIm;
    isSaving: boolean;

    companies: ICompanyIm[];

    paytypes: ListOpt[] = [
        { value: 'POINTS', description: 'Puntos' },
        { value: 'CREDIT_CARD', description: 'Tarjeta de Credito' },
        { value: 'MIXED', description: 'Mixto' }
    ];

    constructor(
        private jhiAlertService: JhiAlertService,
        private bookingService: BookingImService,
        private companyService: CompanyImService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ booking }) => {
            this.booking = booking;
        });
        this.companyService.query().subscribe(
            (res: HttpResponse<ICompanyIm[]>) => {
                this.companies = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.booking.id !== undefined) {
            this.subscribeToSaveResponse(this.bookingService.update(this.booking));
        } else {
            this.subscribeToSaveResponse(this.bookingService.create(this.booking));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBookingIm>>) {
        result.subscribe((res: HttpResponse<IBookingIm>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCompanyById(index: number, item: ICompanyIm) {
        return item.id;
    }
    get booking() {
        return this._booking;
    }

    set booking(booking: IBookingIm) {
        this._booking = booking;
    }
}
