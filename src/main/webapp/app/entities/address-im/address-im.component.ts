import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IAddressIm } from 'app/shared/model/address-im.model';
import { Principal } from 'app/core';
import { AddressImService } from './address-im.service';

@Component({
    selector: 'jhi-address-im',
    templateUrl: './address-im.component.html'
})
export class AddressImComponent implements OnInit, OnDestroy {
    addresses: IAddressIm[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private addressService: AddressImService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.addressService.query().subscribe(
            (res: HttpResponse<IAddressIm[]>) => {
                this.addresses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInAddresses();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAddressIm) {
        return item.id;
    }

    registerChangeInAddresses() {
        this.eventSubscriber = this.eventManager.subscribe('addressListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
