import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPhoneIm } from 'app/shared/model/phone-im.model';
import { Principal } from 'app/core';
import { PhoneImService } from './phone-im.service';

@Component({
    selector: 'jhi-phone-im',
    templateUrl: './phone-im.component.html'
})
export class PhoneImComponent implements OnInit, OnDestroy {
    phones: IPhoneIm[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private phoneService: PhoneImService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.phoneService.query().subscribe(
            (res: HttpResponse<IPhoneIm[]>) => {
                this.phones = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInPhones();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPhoneIm) {
        return item.id;
    }

    registerChangeInPhones() {
        this.eventSubscriber = this.eventManager.subscribe('phoneListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
