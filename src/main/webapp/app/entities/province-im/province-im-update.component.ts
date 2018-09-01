import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IProvinceIm } from 'app/shared/model/province-im.model';
import { ProvinceImService } from './province-im.service';
import { ICountryIm } from 'app/shared/model/country-im.model';
import { CountryImService } from 'app/entities/country-im';

@Component({
    selector: 'jhi-province-im-update',
    templateUrl: './province-im-update.component.html'
})
export class ProvinceImUpdateComponent implements OnInit {
    private _province: IProvinceIm;
    isSaving: boolean;

    countries: ICountryIm[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private provinceService: ProvinceImService,
        private countryService: CountryImService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ province }) => {
            this.province = province;
        });
        this.countryService.query().subscribe(
            (res: HttpResponse<ICountryIm[]>) => {
                this.countries = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.province.id !== undefined) {
            this.subscribeToSaveResponse(this.provinceService.update(this.province));
        } else {
            this.subscribeToSaveResponse(this.provinceService.create(this.province));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IProvinceIm>>) {
        result.subscribe((res: HttpResponse<IProvinceIm>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCountryById(index: number, item: ICountryIm) {
        return item.id;
    }
    get province() {
        return this._province;
    }

    set province(province: IProvinceIm) {
        this._province = province;
    }
}
