/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InteliManagementTestModule } from '../../../test.module';
import { CountryImDeleteDialogComponent } from 'app/entities/country-im/country-im-delete-dialog.component';
import { CountryImService } from 'app/entities/country-im/country-im.service';

describe('Component Tests', () => {
    describe('CountryIm Management Delete Component', () => {
        let comp: CountryImDeleteDialogComponent;
        let fixture: ComponentFixture<CountryImDeleteDialogComponent>;
        let service: CountryImService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [CountryImDeleteDialogComponent]
            })
                .overrideTemplate(CountryImDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CountryImDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CountryImService);
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
