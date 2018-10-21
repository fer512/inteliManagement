import { Component, Input, OnInit } from '@angular/core';
import { VariationService } from 'app/entities/variation';

@Component({
    selector: 'jhi-variation-pending',
    templateUrl: './variation-pending.component.html',
    styleUrls: ['../notification-im.component.css']
})
export class VariationPendingComponent implements OnInit {
    @Input() notification: any;

    variation: any;

    constructor(private variationService: VariationService) {}

    ngOnInit(): void {
        this.variationService.find(this.notification.referenceId).subscribe(data => {
            this.variation = data;
        });
    }
}
