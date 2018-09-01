import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IApprovalsIm } from 'app/shared/model/approvals-im.model';
import { ApprovalsImService } from './approvals-im.service';

@Component({
    selector: 'jhi-approvals-im-delete-dialog',
    templateUrl: './approvals-im-delete-dialog.component.html'
})
export class ApprovalsImDeleteDialogComponent {
    approvals: IApprovalsIm;

    constructor(private approvalsService: ApprovalsImService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.approvalsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'approvalsListModification',
                content: 'Deleted an approvals'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-approvals-im-delete-popup',
    template: ''
})
export class ApprovalsImDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ approvals }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ApprovalsImDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.approvals = approvals;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
