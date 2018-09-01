import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IAddressIm } from 'app/shared/model/address-im.model';
import { AddressImService } from './address-im.service';

@Component({
    selector: 'jhi-address-im-update',
    templateUrl: './address-im-update.component.html'
})
export class AddressImUpdateComponent implements OnInit {
    private _address: IAddressIm;
    isSaving: boolean;

    constructor(private addressService: AddressImService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ address }) => {
            this.address = address;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.address.id !== undefined) {
            this.subscribeToSaveResponse(this.addressService.update(this.address));
        } else {
            this.subscribeToSaveResponse(this.addressService.create(this.address));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAddressIm>>) {
        result.subscribe((res: HttpResponse<IAddressIm>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get address() {
        return this._address;
    }

    set address(address: IAddressIm) {
        this._address = address;
    }
}
