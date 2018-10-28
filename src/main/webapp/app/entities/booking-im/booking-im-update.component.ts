import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IBookingIm } from 'app/shared/model/booking-im.model';
import { BookingImService } from './booking-im.service';
import { ICompanyIm } from 'app/shared/model/company-im.model';
import { CompanyImService } from 'app/entities/company-im';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { IProductByBooking, ProductByBooking } from 'app/shared/model/product-by-booking.model';
import { ProductImService } from 'app/entities/product-im';
import { IProductIm } from 'app/shared/model/product-im.model';
import { isNumber } from '@ng-bootstrap/ng-bootstrap/util/util';

export interface ListOpt {
    value: any;
    description: string;
}

export interface DialogData {
    idReserveLocatorJuniperProduct: string;
    idReserveLocatorJuniperProvider: string;
    idReserveLocatorJuniper: string;
    idReserveLocatorExternal: string;
}

@Component({
    selector: 'jhi-booking-im-update',
    templateUrl: './booking-im-update.component.html'
})
export class BookingImUpdateComponent implements OnInit {
    emailFormControl = new FormControl('', [Validators.required, Validators.email]);

    private _booking: IBookingIm;
    isSaving: boolean;
    companies: ICompanyIm[];

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

    providers: ListOpt[] = [
        { value: 'CON', description: 'Consolid' },
        { value: 'EXP', description: 'Expidia' },
        { value: 'HBS', description: 'HotelBeds' },
        { value: 'TOU', description: 'Tourico' }
    ];

    constructor(
        private jhiAlertService: JhiAlertService,
        private bookingService: BookingImService,
        private companyService: CompanyImService,
        private activatedRoute: ActivatedRoute,
        private productService: ProductImService,
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

        this.bookingService.newBookingId.subscribe(data => {
            if (data != null) {
                this.booking.idTransaction = data;
                this.bookingService.addNewBookingId(null);
            }
        });

        this.productService.query().subscribe(
            (res: HttpResponse<IProductIm[]>) => {
                let productsAux: ListOpt[] = [];
                res.body.forEach(e => {
                    productsAux.push({ value: e.id, description: e.name });
                });
                this.products = productsAux;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    varSum(a: number, b: number): Number {
        let val1: number = !isNaN(a) ? Number(a) : 0;
        let val2: number = !isNaN(b) ? Number(b) : 0;
        return val1 + val2;
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
                products: this.products,
                providers: this.providers
            }
        });

        dialogRef.afterClosed().subscribe((result: ProductByBooking) => {
            if (result != null) {
                if (!this.booking.products) this.booking.products = [];
                this.booking.products.push(result);
            }
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
    constructor(public dialogRef: MatDialogRef<BookingImAddJlDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any) {}

    onNoClick(): void {
        this.dialogRef.close();
    }

    onConfirm() {
        let dto: ProductByBooking = new ProductByBooking();
        dto.idReserveLocatorExternal = this.data.idReserveLocatorExternal;
        dto.idReserveLocatorJuniper = this.data.idReserveLocatorJuniper;
        dto.productId = Number(this.data.idReserveLocatorJuniperProduct);
        dto.idReserveLocatorJuniperProduct = this.data.idReserveLocatorJuniperProduct;
        dto.idReserveLocatorJuniperProvider = this.data.idReserveLocatorJuniperProvider;
        this.dialogRef.close(dto);
    }
}
