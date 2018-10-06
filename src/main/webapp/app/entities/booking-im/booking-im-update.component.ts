import { Component, OnInit, Inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IBookingIm } from 'app/shared/model/booking-im.model';
import { BookingImService } from './booking-im.service';
import { ICompanyIm } from 'app/shared/model/company-im.model';
import { CompanyImService } from 'app/entities/company-im';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { DialogResponseBooking } from 'app/entities/booking-im/model/dialog-response-booking.model';

export interface ListOpt {
    value: string;
    description: string;
}

export interface DialogData {
    idReserveLocatorJuniperProduct: string;
    idReserveLocatorJuniper: string;
    idReserveLocatorExternal: string;
    products: ListOpt[];
}

@Component({
    selector: 'jhi-booking-im-update',
    templateUrl: './booking-im-update.component.html'
})
export class BookingImUpdateComponent implements OnInit {
    private _booking: IBookingIm;
    isSaving: boolean;
    companies: ICompanyIm[];
    DialogListLocatorJuniper: Array<DialogResponseBooking> = [];

    paytypes: ListOpt[] = [
        { value: 'POINTS', description: 'Puntos' },
        { value: 'CREDIT_CARD', description: 'Tarjeta de Credito' },
        { value: 'MIXED', description: 'Mixto' }
    ];

    products: ListOpt[] = [
        { value: 'HOTEL', description: 'Hotel' },
        { value: 'FLIGHT', description: 'Vuelo' },
        { value: 'CAR', description: 'Auto' },
        { value: 'TRANFER', description: ' Traslado' },
        { value: 'TOURS', description: 'Actividades' }
    ];

    constructor(
        private jhiAlertService: JhiAlertService,
        private bookingService: BookingImService,
        private companyService: CompanyImService,
        private activatedRoute: ActivatedRoute,
        public dialog: MatDialog
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
            console.log(this.bookingService.update(this.booking));
            //this.openSnackBar("Un Booking ha sido actualizado con el identificador","");
        } else {
            this.subscribeToSaveResponse(this.bookingService.create(this.booking));
            //this.openSnackBar("Un nuevo Booking ha sido creado con el identificador","");
        }
    }

    openDialog(): void {
        const dialogRef = this.dialog.open(BookingImAddJlDialogComponent, {
            width: '500px',
            data: {
                products: this.products
            }
        });

        dialogRef.afterClosed().subscribe((result: DialogResponseBooking) => {
            this.DialogListLocatorJuniper.push(result);
        });
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

/*Dialog*/

@Component({
    selector: 'booking-im-add-jl-dialog.component',
    templateUrl: 'booking-im-add-jl-dialog.component.html'
})
export class BookingImAddJlDialogComponent {
    constructor(public dialogRef: MatDialogRef<BookingImAddJlDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

    onNoClick(): void {
        this.dialogRef.close();
    }

    onConfirm() {
        let dto: DialogResponseBooking = new DialogResponseBooking();
        dto.idReserveLocatorExternal = this.data.idReserveLocatorExternal;
        dto.idReserveLocatorJuniper = this.data.idReserveLocatorJuniper;
        dto.idReserveLocatorJuniperProduct = this.data.idReserveLocatorJuniperProduct;
        this.dialogRef.close(dto);
    }
}
