import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ICompanyIm } from 'app/shared/model/company-im.model';
import { CompanyImService } from './company-im.service';
import { IAddressIm } from 'app/shared/model/address-im.model';
import { AddressImService } from 'app/entities/address-im';

@Component({
    selector: 'jhi-company-im-update',
    templateUrl: './company-im-update.component.html'
})
export class CompanyImUpdateComponent implements OnInit {
    private _company: ICompanyIm;
    isSaving: boolean;

    addresses: IAddressIm[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private companyService: CompanyImService,
        private addressService: AddressImService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ company }) => {
            this.company = company;
        });
        this.addressService.query({ filter: 'company-is-null' }).subscribe(
            (res: HttpResponse<IAddressIm[]>) => {
                if (!this.company.addressId) {
                    this.addresses = res.body;
                } else {
                    this.addressService.find(this.company.addressId).subscribe(
                        (subRes: HttpResponse<IAddressIm>) => {
                            this.addresses = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.company.id !== undefined) {
            this.subscribeToSaveResponse(this.companyService.update(this.company));
        } else {
            this.subscribeToSaveResponse(this.companyService.create(this.company));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICompanyIm>>) {
        result.subscribe((res: HttpResponse<ICompanyIm>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get company() {
        return this._company;
    }

    set company(company: ICompanyIm) {
        this._company = company;
    }
}
