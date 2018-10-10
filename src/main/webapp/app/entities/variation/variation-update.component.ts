import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IVariation } from 'app/shared/model/variation.model';
import { VariationService } from './variation.service';
import { IUser, UserService } from 'app/core';

export interface ListOpt {
    value: string;
    description: string;
}

@Component({
    selector: 'jhi-variation-update',
    templateUrl: './variation-update.component.html'
})
export class VariationUpdateComponent implements OnInit {
    private _variation: IVariation;
    isSaving: boolean;

    users: IUser[];
    creation_date: string;
    creation_user: string;

    areas: ListOpt[] = [
        { value: 'CUSTOMER_SERVICE', description: 'Customer Service' },
        { value: 'POST_SALE', description: 'Post Venta' },
        { value: 'SALES', description: 'Ventas' },
        { value: 'MARKETING', description: 'Marketing' }
    ];

    campaing: ListOpt[] = [
        { value: 'C1', description: 'Campaña 1' },
        { value: 'C2', description: 'Campaña 2' },
        { value: 'C3', description: 'Campaña 3' },
        { value: 'C4', description: 'Campaña 4' }
    ];

    losstypes: ListOpt[] = [
        { value: 'M1', description: 'Motivo 1' },
        { value: 'M2', description: 'Motivo 2' },
        { value: 'M3', description: 'Motivo 3' },
        { value: 'M4', description: 'Motivo 4' }
    ];

    constructor(
        private jhiAlertService: JhiAlertService,
        private variationService: VariationService,
        private userService: UserService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ variation }) => {
            this.variation = variation;
        });
        this.userService.query().subscribe(
            (res: HttpResponse<IUser[]>) => {
                this.users = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.variation.creation_date = moment(this.creation_date, DATE_TIME_FORMAT);
        if (this.variation.id !== undefined) {
            this.subscribeToSaveResponse(this.variationService.update(this.variation));
        } else {
            this.subscribeToSaveResponse(this.variationService.create(this.variation));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IVariation>>) {
        result.subscribe((res: HttpResponse<IVariation>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackUserById(index: number, item: IUser) {
        return item.id;
    }
    get variation() {
        return this._variation;
    }

    set variation(variation: IVariation) {
        this._variation = variation;
        this.creation_date = moment(variation.creation_date).format(DATE_TIME_FORMAT);
    }
}
