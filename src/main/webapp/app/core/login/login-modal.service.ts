import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material';
import { JhiLoginModalComponent } from 'app/shared/login/login.component';

@Injectable({ providedIn: 'root' })
export class LoginModalService {
    constructor(public dialog: MatDialog) {}

    open(): void {
        this.openDialog();
    }

    openDialog(): void {
        const dialogRef = this.dialog.open(JhiLoginModalComponent, {
            width: '500px',
            data: { name: 'nn', animal: 'aa' }
        });

        dialogRef.afterClosed().subscribe(result => {
            console.log('The dialog was closed');
            // this.animal = result;
        });
    }
}
