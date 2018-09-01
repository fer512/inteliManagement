/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InteliManagementTestModule } from '../../../test.module';
import { EmployeeImDeleteDialogComponent } from 'app/entities/employee-im/employee-im-delete-dialog.component';
import { EmployeeImService } from 'app/entities/employee-im/employee-im.service';

describe('Component Tests', () => {
    describe('EmployeeIm Management Delete Component', () => {
        let comp: EmployeeImDeleteDialogComponent;
        let fixture: ComponentFixture<EmployeeImDeleteDialogComponent>;
        let service: EmployeeImService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [EmployeeImDeleteDialogComponent]
            })
                .overrideTemplate(EmployeeImDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EmployeeImDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EmployeeImService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
