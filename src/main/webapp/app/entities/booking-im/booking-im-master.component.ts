import { Component, OnInit } from '@angular/core';
import { Principal } from 'app/core';
import { BookingImService } from './booking-im.service';
import { Router } from '@angular/router';

@Component({
    selector: 'jhi-booking-im-master',
    templateUrl: './booking-im-master.component.html'
})
export class BookingImMasterComponent implements OnInit {
    currentAccount: any;

    constructor(private principal: Principal) {}

    ngOnInit() {
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
    }
}
