/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InteliManagementTestModule } from '../../../test.module';
import { ProvinceImDeleteDialogComponent } from 'app/entities/province-im/province-im-delete-dialog.component';
import { ProvinceImService } from 'app/entities/province-im/province-im.service';

describe('Component Tests', () => {
    describe('ProvinceIm Management Delete Component', () => {
        let comp: ProvinceImDeleteDialogComponent;
        let fixture: ComponentFixture<ProvinceImDeleteDialogComponent>;
        let service: ProvinceImService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [ProvinceImDeleteDialogComponent]
            })
                .overrideTemplate(ProvinceImDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProvinceImDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProvinceImService);
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
