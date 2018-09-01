import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEmployeeIm } from 'app/shared/model/employee-im.model';

@Component({
    selector: 'jhi-employee-im-detail',
    templateUrl: './employee-im-detail.component.html'
})
export class EmployeeImDetailComponent implements OnInit {
    employee: IEmployeeIm;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ employee }) => {
            this.employee = employee;
        });
    }

    previousState() {
        window.history.back();
    }
}
