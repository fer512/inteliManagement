import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICountryIm } from 'app/shared/model/country-im.model';
import { CountryImService } from './country-im.service';

@Component({
    selector: 'jhi-country-im-delete-dialog',
    templateUrl: './country-im-delete-dialog.component.html'
})
export class CountryImDeleteDialogComponent {
    country: ICountryIm;

    constructor(private countryService: CountryImService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.countryService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'countryListModification',
                content: 'Deleted an country'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-country-im-delete-popup',
    template: ''
})
export class CountryImDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ country }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CountryImDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.country = country;
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
