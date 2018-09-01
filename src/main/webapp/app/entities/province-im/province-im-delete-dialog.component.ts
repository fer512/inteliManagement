import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProvinceIm } from 'app/shared/model/province-im.model';
import { ProvinceImService } from './province-im.service';

@Component({
    selector: 'jhi-province-im-delete-dialog',
    templateUrl: './province-im-delete-dialog.component.html'
})
export class ProvinceImDeleteDialogComponent {
    province: IProvinceIm;

    constructor(private provinceService: ProvinceImService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.provinceService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'provinceListModification',
                content: 'Deleted an province'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-province-im-delete-popup',
    template: ''
})
export class ProvinceImDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ province }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ProvinceImDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.province = province;
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
