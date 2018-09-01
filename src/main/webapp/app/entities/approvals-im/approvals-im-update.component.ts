import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IApprovalsIm } from 'app/shared/model/approvals-im.model';
import { ApprovalsImService } from './approvals-im.service';

@Component({
    selector: 'jhi-approvals-im-update',
    templateUrl: './approvals-im-update.component.html'
})
export class ApprovalsImUpdateComponent implements OnInit {
    private _approvals: IApprovalsIm;
    isSaving: boolean;
    stastDate: string;
    endDate: string;

    constructor(private approvalsService: ApprovalsImService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ approvals }) => {
            this.approvals = approvals;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.approvals.stastDate = moment(this.stastDate, DATE_TIME_FORMAT);
        this.approvals.endDate = moment(this.endDate, DATE_TIME_FORMAT);
        if (this.approvals.id !== undefined) {
            this.subscribeToSaveResponse(this.approvalsService.update(this.approvals));
        } else {
            this.subscribeToSaveResponse(this.approvalsService.create(this.approvals));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IApprovalsIm>>) {
        result.subscribe((res: HttpResponse<IApprovalsIm>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get approvals() {
        return this._approvals;
    }

    set approvals(approvals: IApprovalsIm) {
        this._approvals = approvals;
        this.stastDate = moment(approvals.stastDate).format(DATE_TIME_FORMAT);
        this.endDate = moment(approvals.endDate).format(DATE_TIME_FORMAT);
    }
}
