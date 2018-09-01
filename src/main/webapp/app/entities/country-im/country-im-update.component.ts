import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICountryIm } from 'app/shared/model/country-im.model';
import { CountryImService } from './country-im.service';

@Component({
    selector: 'jhi-country-im-update',
    templateUrl: './country-im-update.component.html'
})
export class CountryImUpdateComponent implements OnInit {
    private _country: ICountryIm;
    isSaving: boolean;

    constructor(private countryService: CountryImService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ country }) => {
            this.country = country;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.country.id !== undefined) {
            this.subscribeToSaveResponse(this.countryService.update(this.country));
        } else {
            this.subscribeToSaveResponse(this.countryService.create(this.country));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICountryIm>>) {
        result.subscribe((res: HttpResponse<ICountryIm>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get country() {
        return this._country;
    }

    set country(country: ICountryIm) {
        this._country = country;
    }
}
