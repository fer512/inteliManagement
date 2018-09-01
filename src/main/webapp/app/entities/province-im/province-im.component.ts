import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IProvinceIm } from 'app/shared/model/province-im.model';
import { Principal } from 'app/core';
import { ProvinceImService } from './province-im.service';

@Component({
    selector: 'jhi-province-im',
    templateUrl: './province-im.component.html'
})
export class ProvinceImComponent implements OnInit, OnDestroy {
    provinces: IProvinceIm[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private provinceService: ProvinceImService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.provinceService.query().subscribe(
            (res: HttpResponse<IProvinceIm[]>) => {
                this.provinces = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInProvinces();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IProvinceIm) {
        return item.id;
    }

    registerChangeInProvinces() {
        this.eventSubscriber = this.eventManager.subscribe('provinceListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
