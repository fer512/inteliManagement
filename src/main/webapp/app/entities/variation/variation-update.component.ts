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

@Component({
    selector: 'jhi-variation-update',
    templateUrl: './variation-update.component.html'
})
export class VariationUpdateComponent implements OnInit {
    private _variation: IVariation;
    isSaving: boolean;

    users: IUser[];
    creation_date: string;

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
