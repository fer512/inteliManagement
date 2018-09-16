import { Component, OnDestroy, OnInit } from '@angular/core';
import { JhiAlertService } from 'ng-jhipster';
import { MatSnackBar } from '@angular/material';

@Component({
    selector: 'jhi-alert',
    template: `
    ---------------
        <div class="alerts" role="alert">
            <div *ngFor="let alert of alerts" [ngClass]="setClasses(alert)">
                <ngb-alert *ngIf="alert && alert.type && alert.msg" [type]="alert.type" (close)="alert.close(alerts)">
                    <pre [innerHTML]="alert.msg"></pre>
                </ngb-alert>
            </div>
        </div>
    --------------------`
})
export class JhiAlertComponent implements OnInit, OnDestroy {
    alerts: any[];

    constructor(private alertService: JhiAlertService, public snackBar: MatSnackBar) {}

    openSnackBar(message: any, action: string) {
        this.snackBar.open(message, action, {
            duration: 2000
        });
    }

    ngOnInit() {
        this.alerts = this.alertService.get();
        this.alerts.forEach(alert => {
            this.openSnackBar(alert.msg, alert.type);
            this.openSnackBar(alert.msg, alert.type);
            this.openSnackBar(alert.msg, alert.type);
            this.openSnackBar(alert.msg, alert.type);
        });
    }

    setClasses(alert) {
        return {
            toast: !!alert.toast,
            [alert.position]: true
        };
    }

    ngOnDestroy() {
        this.alerts = [];
    }
}
