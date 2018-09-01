import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IApprovalsIm } from 'app/shared/model/approvals-im.model';

@Component({
    selector: 'jhi-approvals-im-detail',
    templateUrl: './approvals-im-detail.component.html'
})
export class ApprovalsImDetailComponent implements OnInit {
    approvals: IApprovalsIm;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ approvals }) => {
            this.approvals = approvals;
        });
    }

    previousState() {
        window.history.back();
    }
}
