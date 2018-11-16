import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiAlertService } from 'ng-jhipster';
import { IBookingIm } from 'app/shared/model/booking-im.model';
import { VariationService } from 'app/entities/variation/variation.service';

@Component({
    selector: 'jhi-booking-im-detail',
    templateUrl: './booking-im-detail.component.html'
})
export class BookingImDetailComponent implements OnInit {
    booking: IBookingIm;
    selectedIndex: number = 0;

    constructor(
        private activatedRoute: ActivatedRoute,
        private variationService: VariationService,
        private jhiAlertService: JhiAlertService
    ) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ booking }) => {
            this.booking = booking;
            /** find index to object load by route param jid (JuniperId) and set active tab */
            let refJidActiveTab = null;
            this.activatedRoute.params.subscribe(params => {
                refJidActiveTab = params['jid'];
            });
            if (refJidActiveTab) {
                const idx = this.booking.products.findIndex(x => x.idReserveLocatorJuniper === refJidActiveTab);
                this.selectedIndex = idx;
            }
        });
    }

    approve(id: number) {
        this.variationService.approve(id).subscribe(
            data => {
                this.jhiAlertService.success('ok', null, null);
            },
            error => {
                this.jhiAlertService.error(error.error.detail, null, null);
            }
        );
    }

    rejected(id: number) {
        this.variationService.rejected(id).subscribe(
            data => {
                this.jhiAlertService.success('ok', null, null);
            },
            error => {
                this.jhiAlertService.error(error.error.detail, null, null);
            }
        );
    }

    previousState() {
        window.history.back();
    }
}
