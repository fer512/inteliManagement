import { Component, OnInit } from '@angular/core';
import { Principal } from 'app/core';
import { BookingImService } from './booking-im.service';
import { IBookingIm } from '../../shared/model/booking-im.model';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Component({
    selector: 'jhi-booking-im-master',
    templateUrl: './booking-im-master.component.html'
})
export class BookingImMasterComponent implements OnInit {
    currentAccount: any;
    search = null;
    links = [
        { title: 'Mis Registros', routerlink: '/booking-im' },
        { title: 'Mis Pendientes', routerlink: '' },
        { title: 'Notificaciones', routerlink: '/notification-im' }
    ];
    activeLink = this.links[0];
    background = '';

    constructor(private principal: Principal, private bookingService: BookingImService, private router: Router) {}

    ngOnInit() {
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
    }

    onSearch() {
        let criteria = { 'juniperReservationCost.equals': this.search };
        this.bookingService
            .findByCriteria(criteria)
            .subscribe(
                (res: HttpResponse<IBookingIm[]>) => this.onSaveSuccess(res.body),
                (res: HttpErrorResponse) => this.onSaveError(res.message)
            );
    }

    private onSaveSuccess(list: IBookingIm[]) {
        if (list != null && list.length == 1) {
            this.view(list[0].id);
        }
    }

    view(id: number) {
        let url = '/booking-im/' + id + '/view';
        this.router.navigate([url]);
    }

    private onSaveError(e: any) {}
}
