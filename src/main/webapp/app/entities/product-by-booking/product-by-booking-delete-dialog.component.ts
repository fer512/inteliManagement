import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProductByBooking } from 'app/shared/model/product-by-booking.model';
import { ProductByBookingService } from './product-by-booking.service';

@Component({
    selector: 'jhi-product-by-booking-delete-dialog',
    templateUrl: './product-by-booking-delete-dialog.component.html'
})
export class ProductByBookingDeleteDialogComponent {
    productByBooking: IProductByBooking;

    constructor(
        private productByBookingService: ProductByBookingService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.productByBookingService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'productByBookingListModification',
                content: 'Deleted an productByBooking'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-product-by-booking-delete-popup',
    template: ''
})
export class ProductByBookingDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ productByBooking }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ProductByBookingDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.productByBooking = productByBooking;
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
