import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICompanyIm } from 'app/shared/model/company-im.model';

@Component({
    selector: 'jhi-company-im-detail',
    templateUrl: './company-im-detail.component.html'
})
export class CompanyImDetailComponent implements OnInit {
    company: ICompanyIm;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ company }) => {
            this.company = company;
        });
    }

    previousState() {
        window.history.back();
    }
}
