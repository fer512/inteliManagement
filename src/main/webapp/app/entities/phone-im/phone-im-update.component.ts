import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IPhoneIm } from 'app/shared/model/phone-im.model';
import { PhoneImService } from './phone-im.service';
import { IEmployeeIm } from 'app/shared/model/employee-im.model';
import { EmployeeImService } from 'app/entities/employee-im';

@Component({
    selector: 'jhi-phone-im-update',
    templateUrl: './phone-im-update.component.html'
})
export class PhoneImUpdateComponent implements OnInit {
    private _phone: IPhoneIm;
    isSaving: boolean;

    employees: IEmployeeIm[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private phoneService: PhoneImService,
        private employeeService: EmployeeImService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ phone }) => {
            this.phone = phone;
        });
        this.employeeService.query().subscribe(
            (res: HttpResponse<IEmployeeIm[]>) => {
                this.employees = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.phone.id !== undefined) {
            this.subscribeToSaveResponse(this.phoneService.update(this.phone));
        } else {
            this.subscribeToSaveResponse(this.phoneService.create(this.phone));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPhoneIm>>) {
        result.subscribe((res: HttpResponse<IPhoneIm>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackEmployeeById(index: number, item: IEmployeeIm) {
        return item.id;
    }
    get phone() {
        return this._phone;
    }

    set phone(phone: IPhoneIm) {
        this._phone = phone;
    }
}
