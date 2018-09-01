import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IProviderIm } from 'app/shared/model/provider-im.model';
import { ProviderImService } from './provider-im.service';
import { IAddressIm } from 'app/shared/model/address-im.model';
import { AddressImService } from 'app/entities/address-im';
import { ICompanyIm } from 'app/shared/model/company-im.model';
import { CompanyImService } from 'app/entities/company-im';

@Component({
    selector: 'jhi-provider-im-update',
    templateUrl: './provider-im-update.component.html'
})
export class ProviderImUpdateComponent implements OnInit {
    private _provider: IProviderIm;
    isSaving: boolean;

    addresses: IAddressIm[];

    companies: ICompanyIm[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private providerService: ProviderImService,
        private addressService: AddressImService,
        private companyService: CompanyImService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ provider }) => {
            this.provider = provider;
        });
        this.addressService.query({ filter: 'provider-is-null' }).subscribe(
            (res: HttpResponse<IAddressIm[]>) => {
                if (!this.provider.addressId) {
                    this.addresses = res.body;
                } else {
                    this.addressService.find(this.provider.addressId).subscribe(
                        (subRes: HttpResponse<IAddressIm>) => {
                            this.addresses = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
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
        if (this.provider.id !== undefined) {
            this.subscribeToSaveResponse(this.providerService.update(this.provider));
        } else {
            this.subscribeToSaveResponse(this.providerService.create(this.provider));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IProviderIm>>) {
        result.subscribe((res: HttpResponse<IProviderIm>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackAddressById(index: number, item: IAddressIm) {
        return item.id;
    }

    trackCompanyById(index: number, item: ICompanyIm) {
        return item.id;
    }
    get provider() {
        return this._provider;
    }

    set provider(provider: IProviderIm) {
        this._provider = provider;
    }
}
