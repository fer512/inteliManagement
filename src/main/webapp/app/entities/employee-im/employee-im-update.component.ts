import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IEmployeeIm } from 'app/shared/model/employee-im.model';
import { EmployeeImService } from './employee-im.service';
import { IAddressIm } from 'app/shared/model/address-im.model';
import { AddressImService } from 'app/entities/address-im';
import { IUser, UserService } from 'app/core';
import { ICompanyIm } from 'app/shared/model/company-im.model';
import { CompanyImService } from 'app/entities/company-im';

@Component({
    selector: 'jhi-employee-im-update',
    templateUrl: './employee-im-update.component.html'
})
export class EmployeeImUpdateComponent implements OnInit {
    private _employee: IEmployeeIm;
    isSaving: boolean;

    addresses: IAddressIm[];

    users: IUser[];

    employees: IEmployeeIm[];

    companies: ICompanyIm[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private employeeService: EmployeeImService,
        private addressService: AddressImService,
        private userService: UserService,
        private companyService: CompanyImService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ employee }) => {
            this.employee = employee;
        });
        this.addressService.query({ filter: 'employee-is-null' }).subscribe(
            (res: HttpResponse<IAddressIm[]>) => {
                if (!this.employee.addressId) {
                    this.addresses = res.body;
                } else {
                    this.addressService.find(this.employee.addressId).subscribe(
                        (subRes: HttpResponse<IAddressIm>) => {
                            this.addresses = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.userService.query().subscribe(
            (res: HttpResponse<IUser[]>) => {
                this.users = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.employeeService.query().subscribe(
            (res: HttpResponse<IEmployeeIm[]>) => {
                this.employees = res.body;
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
        if (this.employee.id !== undefined) {
            this.subscribeToSaveResponse(this.employeeService.update(this.employee));
        } else {
            this.subscribeToSaveResponse(this.employeeService.create(this.employee));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEmployeeIm>>) {
        result.subscribe((res: HttpResponse<IEmployeeIm>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackUserById(index: number, item: IUser) {
        return item.id;
    }

    trackEmployeeById(index: number, item: IEmployeeIm) {
        return item.id;
    }

    trackCompanyById(index: number, item: ICompanyIm) {
        return item.id;
    }
    get employee() {
        return this._employee;
    }

    set employee(employee: IEmployeeIm) {
        this._employee = employee;
    }
}
