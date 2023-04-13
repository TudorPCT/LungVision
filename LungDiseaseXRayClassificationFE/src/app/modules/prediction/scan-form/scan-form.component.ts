import {Component, ElementRef, EventEmitter, OnInit, Output, Renderer2} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-scan-form',
  templateUrl: './scan-form.component.html',
  styleUrls: ['./scan-form.component.css']
})
export class ScanFormComponent implements OnInit{
  @Output() scan = new EventEmitter<string>();
  phase: string;
  uploadedFiles: any[] = [];
  chooseLabel?: string;
  uploadLabel?: string;
  cancelLabel?: string;
  info?: string;
  reader : FileReader;
  scanSrc?: string;
  constructor(public translate: TranslateService, private renderer: Renderer2, private el: ElementRef) {
    this.phase = 'choose';

    this.reader = new FileReader();
    this.reader.onload = () => {
      this.scanSrc = this.reader.result as string;
      this.scan.emit(this.scanSrc);
    };
  }

  ngOnInit() {
    this.scan.emit(undefined);

    this.chooseLabel = this.translate.instant('prediction.scan.choose');
    this.uploadLabel = this.translate.instant('prediction.scan.upload');
    this.cancelLabel = this.translate.instant('prediction.scan.cancel');
    this.info = this.translate.instant('prediction.scan.info');

    this.scanSrc = undefined;
  }

  onPredict($event: any) {
    this.phase = 'loading'
    this.disableElement('#file-upload');

    // Call predict service

    this.enableElement('#file-upload');
  }

  onSelect($event : any) {
    this.reader.readAsDataURL($event.files[0] as File);
  }

  onCancel($event: any) {
    this.scan.emit(undefined);
    this.scanSrc = undefined;
    this.phase = 'choose';
  }

  disableElement(elementId: string) {
    const div = this.el.nativeElement.querySelector(elementId);
    this.renderer.setStyle(div, 'pointer-events', 'none');
    this.renderer.setStyle(div, 'opacity', '0.4');
  }

  enableElement(elementId: string) {
    const div = this.el.nativeElement.querySelector(elementId);
    this.renderer.setStyle(div, 'pointer-events', 'all');
    this.renderer.setStyle(div, 'opacity', '1');
  }

}
