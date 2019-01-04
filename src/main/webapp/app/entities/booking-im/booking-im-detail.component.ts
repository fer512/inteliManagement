import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiAlertService } from 'ng-jhipster';
import { IBookingIm } from 'app/shared/model/booking-im.model';
import { VariationService } from 'app/entities/variation/variation.service';
import { BookingImService } from 'app/entities/booking-im/booking-im.service';

@Component({
    selector: 'jhi-booking-im-detail',
    templateUrl: './booking-im-detail.component.html'
})
export class BookingImDetailComponent implements OnInit {
    booking: IBookingIm;
    selectedIndex: number = 0;
    panelOpenState;
    constructor(
        private activatedRoute: ActivatedRoute,
        private variationService: VariationService,
        private jhiAlertService: JhiAlertService,
        private bookingService: BookingImService
    ) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ booking }) => {
            this.booking = booking;
            this.buildValues();
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

    buildValues() {
        this.booking.products.forEach(p => {
            p['TotExtraCharge'] = 0;
            p['TotNewCharge'] = 0;
            p['TotNewCost'] = 0;
            p['TotNewBenefit'] = 0;
            p['TotRefundInCash'] = 0;
            p['TotRefundInPoints'] = 0;
            p.variations.forEach(v => {
                if (v.approvals.status == 'APPROVED') {
                    p['TotExtraCharge'] = p['TotExtraCharge'] + v.extraCharge;
                    p['TotNewCharge'] = p['TotNewCharge'] + v.newCharge;
                    p['TotNewCost'] = p['TotNewCost'] + v.newCost;
                    p['TotNewBenefit'] = p['TotNewBenefit'] + v.newBenefit;
                    p['TotRefundInCash'] = p['TotRefundInCash'] + v.refundInCash;
                    p['TotRefundInPoints'] = p['TotRefundInPoints'] + v.refundInPoints;
                }
                this.variationService.canApproveRejected(v.id).subscribe(response => {
                    v['privilege'] = response.body;
                });
            });
        });
    }

    recover(data: any) {}

    approve(id: number) {
        this.variationService.approve(id).subscribe(
            data => {
                this.jhiAlertService.success('ok', null, null);
                this.bookingService.find(this.booking.id).subscribe(response => {
                    console.log(response.body);
                    this.booking = response.body;
                    this.buildValues();
                });
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
